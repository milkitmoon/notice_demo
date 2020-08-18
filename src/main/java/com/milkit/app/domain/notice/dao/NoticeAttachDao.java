package com.milkit.app.domain.notice.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.notice.NoticeAttach;
import com.milkit.app.domain.userinfo.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoticeAttachDao extends JpaRepository<NoticeAttach, Long>, JpaSpecificationExecutor<NoticeAttach>  {
 
	@Transactional
	@Modifying
	@Query(value="UPDATE NoticeAttach SET USE_YN = 'N', UPD_TIME = now(), UPD_USER = :updUser WHERE ID = :id", nativeQuery=false)
	public Integer disable(@Param("id") Long id, @Param("updUser") String updUser);


}
