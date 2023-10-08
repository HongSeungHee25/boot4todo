package org.iclass.mvc.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {	//javax.validation 으로 서버에서 유효성 검사
	private long r;
	private long tno;

	@NotEmpty		//내용이 없는것은 허용 X
	@Size(min = 3)	//최소 글자수
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	@Future 		//서버에서 값 검증하는 애노테이션 - 오늘 이후의 날짜만 유효함. (LocalDate만 가능)
	private LocalDate dueDate;
	
	@NotEmpty private String writer;
	private boolean finished;
}
/*
 * CREATE TABLE tbl_todo (
	tno number(6) PRIMARY KEY,
	title varchar2(100) NOT NULL,
	dueDate DATE NOT NULL,
	writer varchar2(50) NOT NULL,
	finished number(2) DEFAULT 0
);

create sequence todoSeq start with 10111;

 * 
 */

/*
 * 마이바티스 날짜 타입을 Date에서 LocalDate 로 변경하기
 * <dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-typehandlers-jsr310</artifactId>
  <version>1.0.2</version>
</dependency>
 * 
 * 
 */
