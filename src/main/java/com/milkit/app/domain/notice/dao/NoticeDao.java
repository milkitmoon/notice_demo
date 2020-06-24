package com.milkit.app.domain.notice.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.milkit.app.domain.notice.Notice;
import com.milkit.app.domain.userinfo.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoticeDao extends JpaRepository<Notice, Long>, JpaSpecificationExecutor<Notice>  {
 
	@Query(value = "SELECT NOTICE_BULLETIN_SEQ.NEXTVAL FROM dual", nativeQuery = true)
    public Long getSeqNextVal();
	
	@Transactional
	@Modifying
	@Query(value="UPDATE Notice SET TITLE = :#{#notice.title}, CONTENT = :#{#notice.content}, UPD_TIME = now(), UPD_USER = :#{#notice.updUser} WHERE ID = :#{#notice.id}", nativeQuery=false)
	public Integer update(@Param("notice") Notice notice);

	@Transactional
	@Modifying
	@Query(value="UPDATE Notice SET USE_YN = 'N', UPD_TIME = now(), UPD_USER = :updUser WHERE ID = :id", nativeQuery=false)
	public Integer disable(@Param("id") Long id, @Param("updUser") String updUser);

	@Transactional
	@Modifying
	@Query(value="UPDATE Notice SET READ_NUM = READ_NUM + 1 WHERE ID = :id", nativeQuery=false)
	public void updateReadNum(@Param("id") Long id);

}
