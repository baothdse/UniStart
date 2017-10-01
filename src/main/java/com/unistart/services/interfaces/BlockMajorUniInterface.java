package com.unistart.services.interfaces;

import com.unistart.entities.Block;
import com.unistart.entities.BlockMajorUniversity;
import com.unistart.entities.Major;
import com.unistart.entities.ScoreHistory;

public interface BlockMajorUniInterface {
    boolean saveBlockOfMajor(int blockId, int majorId);
    boolean saveScore(int blockMajorUniId, Double score, int year);
    BlockMajorUniversity findByBlockAndMajor(int blockId, int majorId);
    ScoreHistory findByIdAndYear (int blockMajorUniId, int year);
    boolean updateScore(int blockMajorUniId, Double score, int year);
}
