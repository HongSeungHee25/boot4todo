package org.iclass.mvc.service;

import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
import org.iclass.mvc.dto.TodoDto;

import java.util.List;

public interface TodoService {
	int register(TodoDto todoDto);
	List<TodoDto> getAll();
	TodoDto getOne(long tno);
	void remove(long tno);
	int modify(TodoDto todoDto);
	long getCount(PageRequestDTO pageRequestDTO);
	List<TodoDto> selectList(PageRequestDTO pageDTO);
	
}
