package com.trollmarket.service;

import com.trollmarket.dao.AccountRepository;
import com.trollmarket.dao.SellerRepository;
import com.trollmarket.dto.account.RegisterDTO;
import com.trollmarket.dto.profile.GetProfilDTO;
import com.trollmarket.entity.Account;
import com.trollmarket.entity.OrderDetail;
import com.trollmarket.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisterDTO registerDTO) {
        String hashPassword = passwordEncoder.encode(registerDTO.getPassword());
        Account account = new Account(registerDTO.getUsername(), hashPassword,
                registerDTO.getRole());
        Seller seller = new Seller(registerDTO.getId(), registerDTO.getFirstName(),
                registerDTO.getLastName(), registerDTO.getAddress(),
                BigDecimal.valueOf(0),account);
        sellerRepository.save(seller);
    }

    @Override
    public Seller findSellerByUsername(String name) {
        System.out.println(sellerRepository.findByUsername(name));
        return sellerRepository.findByUsername(name);
    }

    @Override
    public List<Seller> findAllSeller() {
        return sellerRepository.findAll();
    }

    @Override
    public GetProfilDTO findProfilByUsername(String username) {
        return sellerRepository.findProfilByUsername(username);
    }

    @Override
    public List<OrderDetail> findAllTransactionSeller(String username) {
        Seller seller = sellerRepository.findByUsername(username);
        return sellerRepository.findAllTransactionSeller(seller.getId());
    }
}
