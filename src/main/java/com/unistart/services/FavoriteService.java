package com.unistart.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.services.interfaces.FavoriteInterface;


@Service
@Transactional
public class FavoriteService implements FavoriteInterface{

}
