package com.edoc.backend.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edoc.backend.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="certificates")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificate implements Serializable {
	
	private static final long serialVersionUID = -6767962525589390810L;

	@Column(columnDefinition = "text", name = "document_link")
	private String documentLink;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "document_type")
	private DocumentType documentType;
	
	// mapping to be done
	@Id
	@ManyToOne
	@JoinColumn(name = "admission_no")
	private Admission admission;
}
