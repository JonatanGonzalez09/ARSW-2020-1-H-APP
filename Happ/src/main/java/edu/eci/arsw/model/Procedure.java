package edu.eci.arsw.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="procedure")
@NamedQuery(name="Procedure.findAll", query="SELECT p FROM Procedure p")
public class Procedure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="procedure_id", unique=true, nullable=false)
	private Integer procedureId;

	@Column(nullable=false, length=255)
	private String description;

	@Column(nullable=false, length=255)
	private String name;

	//bi-directional many-to-one association to Undergoes
	@OneToMany(mappedBy="procedure")
	private List<Undergoes> undergoes;

	public Procedure() {
	}

	public Integer getProcedureId() {
		return this.procedureId;
	}

	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String deccription) {
		this.description = deccription;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Undergoes> getUndergoes() {
		return this.undergoes;
	}

	public void setUndergoes(List<Undergoes> undergoes) {
		this.undergoes = undergoes;
	}

	public Undergoes addUndergoe(Undergoes undergoes) {
		getUndergoes().add(undergoes);
		undergoes.setProcedure(this);

		return undergoes;
	}

	public Undergoes removeUndergoe(Undergoes undergoes) {
		getUndergoes().remove(undergoes);
		undergoes.setProcedure(null);

		return undergoes;
	}

}
