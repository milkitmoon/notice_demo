package com.milkit.app.domain.notice.dao;

import org.springframework.data.jpa.domain.Specification;

import com.milkit.app.domain.notice.Notice;

public class NoticeSpec {
	
	public static Specification<Notice> withUseYN(String useYN) {
        return (Specification<Notice>) ((root, query, builder) -> 
        	builder.equal(root.get("useYN"), useYN)
        );
    }
}
