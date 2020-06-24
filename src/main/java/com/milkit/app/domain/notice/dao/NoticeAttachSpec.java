package com.milkit.app.domain.notice.dao;

import org.springframework.data.jpa.domain.Specification;

import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.NoticeAttach;

public class NoticeAttachSpec {
	
	public static Specification<NoticeAttach> withNoticeID(Long noticeID) {
        return (Specification<NoticeAttach>) ((root, query, builder) -> 
        	builder.equal(root.get("noticeID"), noticeID)
        );
    }
	
	public static Specification<NoticeAttach> withUseYN(String useYN) {
        return (Specification<NoticeAttach>) ((root, query, builder) -> 
        	builder.equal(root.get("useYN"), useYN)
        );
    }
}
