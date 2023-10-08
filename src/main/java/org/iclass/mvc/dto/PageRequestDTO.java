package org.iclass.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PageRequestDTO {
	
	@Builder.Default
	@Min(value = 1)
	private int page =1;
	
	@Builder.Default @Min(value = 5) @Max(value = 100)
	private int size=5;      //size 는 한 개 페이지 글 갯수
	
	public int getSkip() {
		return (page-1)*10;
		
	}
	
	private int start;
	private int end;

	private String[] types;
	private String keyword;
	private boolean finished;
	private LocalDate from;
	private LocalDate to;

	//▼ 검색 기능 제외하고 테스트용으로 남겨둠
	public static PageRequestDTO of(int page,int size) {  
		
		int startNo=(page-1)* size+1;    //글목록 시작행번호(rownum)
		int endNo = startNo + (size-1);
		
		
		return PageRequestDTO.builder()
				.page(page)
				.size(size)
				.start(startNo)
				.end(endNo)
				.build();
	}
	
	public void setDatas(){	//오라클에서만 필요 (mysql은 limit 이라는 연산자 사용)
		start = (page-1) * size + 1;	//글 목록 시작행번호(rownum)
		end = start + (size - 1);

	}

	//페이지 이동 파라미터
	private String link;

	public String getLink(){    // 관련된 view 는 list.hrml에서 하세요. 교재 391p
		if(link==null){
			StringBuilder builder = new StringBuilder();
			builder.append("page=" + this.page);
//			builder.append("&size=" + this.size);
			// 완료 여부가 체크됐을 때만
			if(this.finished){
				builder.append("&finished=true");
			}
			// 검색 필드를 선택하고 keyword 입력했을 때만
			if(this.types != null && this.types.length > 0){
				for (int i = 0; i < types.length; i++) {
					builder.append("&types="+types[i]);
				}
			}
			if(keyword != null) {
				try {
					builder.append("&keyword=" + URLEncoder.encode(this.keyword, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}

			if(this.from!=null && this.to!=null){
				builder.append("&from=" + this.from);
				builder.append("&to=" + this.to);
			}
			this.link= builder.toString();
		}
		return this.link;
	}

	public boolean checkType(String type){
		if(types == null || types.length==0)
				return false;
		return Arrays.stream(types)
				.anyMatch(type::equals);
		//types에 저장된 배열을 스트림으로 변환하고 그 스트림과 인자로 전달된 type 값이 일치하는 것이 있으면 참.
		//검색 조건에 따라  화면에 표시를 해주기 위한 메소드이며 list.jsp에서 사용합니다.
	}


}
