package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Result;
import domain.repository.ResultRepository;
import service.ResultService;

@Service
public class ResultServiceImpl implements ResultService{
	
	@Autowired
	private ResultRepository resultRepository;
	
	public List<Result> getResultsNames(){
		return resultRepository.getResultsNames();
	}
}
