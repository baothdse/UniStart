package com.unistart.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unistart.services.interfaces.ArticleInterface;

@Service
@Transactional
public class ArticleService implements ArticleInterface{

}
