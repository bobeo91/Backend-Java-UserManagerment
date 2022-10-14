package com.npv.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "`Group`")
@Data
public class Group{
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`name`", length = 50, nullable = false)
	private String name;
	
	@Column(name = "`type`", columnDefinition = "ENUM('FRONTEND', 'BACKEND', 'FULLSTACK')")
	@Enumerated(EnumType.STRING)
	private GroupType type;
	
	/*Filter by Date*/
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;
	
	/*Filter by DateTime*/
//	@Column(name = "created_at")
//	private Instant createdAt;

	@Column(name = "total_member", nullable = false)
	private int totalMember;
	
	public enum GroupType {
		FRONTEND, BACKEND, FULLSTACK;
		
		public static GroupType toEnum(String type) {
			for(GroupType item : GroupType.values()) {
				if (item.toString().equals(type)) return item;
			}
			return null;
		}
	}
}

