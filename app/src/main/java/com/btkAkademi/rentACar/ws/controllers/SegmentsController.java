package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.dtos.SegmentListDto;
import com.btkAkademi.rentACar.business.requests.segmentRequests.CreateSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/segments")
@CrossOrigin
public class SegmentsController {
	private SegmentService segmentService;

	@Autowired
	public SegmentsController(SegmentService segmentService) {
		super();
		this.segmentService = segmentService;
	}
	@GetMapping("getall")
	public DataResult<List<SegmentListDto>> getAll() {
		return this.segmentService.getAll();
	}
	@PostMapping("add")
	public Result add(@RequestBody CreateSegmentRequest createSegmentResquest) {
		return this.segmentService.add(createSegmentResquest);
	}

}
