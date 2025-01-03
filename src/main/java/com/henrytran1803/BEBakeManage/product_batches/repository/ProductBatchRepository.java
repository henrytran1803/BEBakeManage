package com.henrytran1803.BEBakeManage.product_batches.repository;

import com.henrytran1803.BEBakeManage.product_batches.entity.ProductBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductBatchRepository extends JpaRepository<ProductBatch,Integer> {
    List<ProductBatch> findByProductIdAndStatusIn(int productId, List<String> statuses);

    @Query("SELECT MAX(pb.expirationDate) FROM ProductBatch pb WHERE pb.id IN :productBatchIds")
    LocalDateTime findMaxExpiryDateByBatchIds(List<Integer> productBatchIds);

    @Query("SELECT p.discountLimit FROM Product p JOIN p.productBatches pb WHERE pb.id = :productBatchId")
    Double findDiscountLimitByProductBatchId(@Param("productBatchId") Integer productBatchId);


    @Query("SELECT pb.dailyDiscount FROM ProductBatch pb WHERE pb.id = :productBatchId")
    Integer findDailyDiscountByProductBatchId(Long productBatchId);

    @Query(value = """
            SELECT 
                pb.id AS id,
                p.name AS name,
                pb.status AS status,
                COALESCE(pb.quantity, 0) AS quantity,
                DATE(pb.expiration_date) AS dateExpiry,
                DATEDIFF(DATE(pb.expiration_date), CURDATE()) AS countDown
            FROM 
                products p
                INNER JOIN product_batches pb ON p.id = pb.product_id
            WHERE 
                pb.status IN (:statuses)
            """, nativeQuery = true)
    List<Object[]> findProductBatchDetailsByStatuses(@Param("statuses") List<String> statuses);


    long countByStatus(String status);
}