package com.u8.repository;

import com.u8.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByIsSendAndIsPayAndApplyDeleteAndIsDeleteAndIsCancelAndIsRecycle
            (Integer isSend, Integer isPay, Integer applyDelete, Integer isDelete,Integer isCancel,Integer isRecycle);
}
