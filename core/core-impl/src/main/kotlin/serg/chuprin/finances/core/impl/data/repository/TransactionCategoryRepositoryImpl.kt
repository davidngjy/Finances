package serg.chuprin.finances.core.impl.data.repository

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import serg.chuprin.finances.core.api.domain.model.Id
import serg.chuprin.finances.core.api.domain.model.TransactionCategory
import serg.chuprin.finances.core.api.domain.model.TransactionCategoryType
import serg.chuprin.finances.core.api.domain.repository.TransactionCategoryRepository
import serg.chuprin.finances.core.impl.data.datasource.assets.PredefinedTransactionCategoriesDataSource
import serg.chuprin.finances.core.impl.data.datasource.assets.TransactionCategoryAssetDto
import serg.chuprin.finances.core.impl.data.datasource.firebase.FirebaseTransactionCategoryDataSource
import serg.chuprin.finances.core.impl.data.mapper.category.FirebaseTransactionCategoryMapper
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 19.04.2020.
 */
internal class TransactionCategoryRepositoryImpl @Inject constructor(
    private val mapper: FirebaseTransactionCategoryMapper,
    private val firebaseDataSource: FirebaseTransactionCategoryDataSource,
    private val predefinedCategoriesDataSource: PredefinedTransactionCategoriesDataSource
) : TransactionCategoryRepository {

    override suspend fun createPredefinedCategories(userId: Id) {
        coroutineScope {
            val allCategories = predefinedCategoriesDataSource.getCategories().run {
                (expenseCategories + incomeCategories).mapNotNull { dto -> dto.map(userId) }
            }
            firebaseDataSource.createTransactions(allCategories)
        }
    }

    override fun categoriesFlow(categoryIds: List<Id>): Flow<List<TransactionCategory>> {
        return firebaseDataSource
            .categoriesFlow(categoryIds)
            .map { categories -> categories.mapNotNull(mapper::mapFromSnapshot) }
    }

    private fun TransactionCategoryAssetDto.map(userId: Id): TransactionCategory? {
        val type = if (isIncome) {
            TransactionCategoryType.INCOME
        } else {
            TransactionCategoryType.EXPENSE
        }
        return TransactionCategory.create(
            id = id,
            type = type,
            name = name,
            ownerId = userId.value,
            parentCategoryId = parentCategoryId
        )
    }

}