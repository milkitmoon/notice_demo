package com.milkit.app.domain.userinfo;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;




@Table(name = "USER_INFO")
@Entity
public class UserInfo implements UserDetails {
 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "USER_ID")
    private String userID;
    
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_NM")
    private String userNM;

    @Column(name = "AUTH_ROLE")
    private String authRole;
    
    @Column(name = "USE_YN")
    private String useYN;
    
    @Column(name = "INST_TIME")
    private Date instTime;
    @Column(name = "UPD_TIME")
    private Date updTime;
    @Column(name = "INST_USER")
    private String instUser;
    @Column(name = "UPD_USER")
    private String updUser;


    public UserInfo() {}
    public UserInfo(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserNM() {
		return userNM;
	}
	public void setUserNM(String userNM) {
		this.userNM = userNM;
	}
	public String getAuthRole() {
		return authRole;
	}
	public void setAuthRole(String authRole) {
		this.authRole = authRole;
	}
	public String getUseYN() {
		return useYN;
	}
	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}
	public Date getInstTime() {
		return instTime;
	}
	public void setInstTime(Date instTime) {
		this.instTime = instTime;
	}
	public Date getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	public String getInstUser() {
		return instUser;
	}
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}
	public String getUpdUser() {
		return updUser;
	}
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if(authRole.equals(Role.ADMIN.getValue())) {
        	grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
        	grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        
		return grantedAuthorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return userID;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		boolean isEnabled = false;
		
		if(useYN != null && useYN.equals("Y")) {
			isEnabled = true;
		}
		return isEnabled;
	}
	
	
	@Override  
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.SHORT_PREFIX_STYLE
		);
	}
    
}