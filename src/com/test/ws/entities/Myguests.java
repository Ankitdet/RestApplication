package com.test.ws.entities;

// Generated Jan 1, 2018 2:31:13 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Myguests generated by hbm2java
 */
@Entity
@Table(name = "myguests", catalog = "xboxlive_akdm")
public class Myguests implements java.io.Serializable {

	private MyguestsId id;

	public Myguests() {
	}

	public Myguests(MyguestsId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "firstname", column = @Column(name = "firstname", nullable = false, length = 30)),
			@AttributeOverride(name = "lastname", column = @Column(name = "lastname", nullable = false, length = 30)),
			@AttributeOverride(name = "email", column = @Column(name = "email", length = 50)),
			@AttributeOverride(name = "regDate", column = @Column(name = "reg_date", nullable = false, length = 19)) })
	public MyguestsId getId() {
		return this.id;
	}

	public void setId(MyguestsId id) {
		this.id = id;
	}

}