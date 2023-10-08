package org.iclass.mvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.mvc.dao.TodoMapper;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
import org.iclass.mvc.dto.TodoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	private final TodoMapper todoMapper;

	@Override
	public int register(TodoDto todoDto) {
		log.info(">>>>>>>>>> todoDto : {}", todoDto);

		return todoMapper.insert(todoDto);
	}

	@Override
	public List<TodoDto> getAll() {
		return todoMapper.selectAll();
	}

	@Override
	public TodoDto getOne(long tno) {
		return todoMapper.selectOne(tno);
	}

	@Override
	public void remove(long tno) {
		todoMapper.delete(tno);

	}

	@Override
	public int modify(TodoDto todoDto) {
		// TODO Auto-generated method stub
		return todoMapper.update(todoDto);
	}
	
	@Override
	public long getCount(PageRequestDTO pageRequestDTO ) {
		// TODO Auto-generated method stub
		return todoMapper.getCount(pageRequestDTO);
	}

	@Override
	public List<TodoDto> selectList(PageRequestDTO dto){

		dto.setSize(5);			//한 페이지에 보이는 글의 갯수 설정
		dto.setDatas();				//start 와 end 계산
		List<TodoDto> list = todoMapper.selectList(dto);		//글 목록

		//페이지 목록 구현은 예정.
		return list;
	}
	public PageResponseDTO<TodoDto> ListWithSearch(PageRequestDTO pageDTO) {
		pageDTO.setSize(5);
		pageDTO.setDatas();
		List<TodoDto> dtoList = todoMapper.selectList(pageDTO);
		long total = todoMapper.getCount(pageDTO);

		PageResponseDTO<TodoDto> pageResponseDTO = PageResponseDTO.<TodoDto>withAll()
				.pageRequestDTO(pageDTO)
				.list(dtoList)
				.total(total)
				.build();

		return pageResponseDTO;
	}


}
