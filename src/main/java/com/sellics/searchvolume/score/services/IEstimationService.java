package com.sellics.searchvolume.score.services;


import com.sellics.searchvolume.score.domain.Estimation;

import java.io.IOException;

/**
 * @author Mairelin
 * Interface to be implemented by the classes to get the score
 */
public interface IEstimationService  {

     /**
      * @author Mairelin
      * @param keyWord
      * @return Receive a keyWord and returns an estimation score from 0 to 100
      */
     Estimation getEstimation(String keyWord) throws IOException;
}
