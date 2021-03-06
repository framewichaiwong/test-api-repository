package com.example.menu.repository;

import com.example.menu.entities.ImageSlipTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;

public interface ImageSlipTransferRepository extends JpaRepository<ImageSlipTransfer,Integer> {
    List<ImageSlipTransfer> findByTableCheckBillId(int tableCheckBillId);
}
