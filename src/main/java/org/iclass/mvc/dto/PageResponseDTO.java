package org.iclass.mvc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.iclass.mvc.controller.TodoController;

import java.util.List;


@Getter
@ToString
@Setter
public class PageResponseDTO<TodoDto> {
	
	private int page ;
	private int pageSize;      
	private long total;
	
	private int start;			//페이지 목록 시작
	private int end;			//         마지막
	private int last;			//페이지 목록 없이 앞,뒤 페이지 이동을 위해 변경함.
	
	private boolean prev;
	private boolean next;
	private List<TodoDto> dtoList;
	
	@Builder(builderMethodName = "withAll")
	public PageResponseDTO (PageRequestDTO pageRequestDTO , List<TodoDto> list , long total) {
		this.page = pageRequestDTO.getPage();
		this.pageSize = pageRequestDTO.getSize();
	
		this.total=total;
		this.dtoList = list;
		
		this.end = (int)(Math.ceil(this.page/10.0))*10;
		this.start = this.end-9;
		
		this.last = (int)(Math.ceil((total/(double)pageSize)));	//마지막페이지 번호
		this.end = end > last ? last: end;
		
		this.prev = this.start>1;
		this.next = total > this.end * this.pageSize;
	}
	
}
