package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.dtos.SegmentListDto;
import com.btkAkademi.rentACar.business.requests.segmentRequests.CreateSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.SegmentDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.Segment;

@Service
public class SegmentManager implements SegmentService{

	private SegmentDao segmentDao;
	private ModelMapperService modelMapperService;
	public SegmentManager(SegmentDao segmentDao, ModelMapperService modelMapperService) {
		super();
		this.segmentDao = segmentDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public Result add(CreateSegmentRequest createSegmentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<SegmentListDto>> getAll() {
		List<Segment> segmentList =this.segmentDao.findAll();
		List<SegmentListDto> response = (List<SegmentListDto>) segmentList.stream()
				          .map(segment->modelMapperService.forDto().map(segment, SegmentListDto.class))
				          .collect(Collectors.toList());
		return new SuccessDataResult<List<SegmentListDto>>(response);
	}

}
