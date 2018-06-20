package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Turma extends Model{
	
	
	public String nomeTurma;
	public int numeroSala;
	public String turno;
	
	
	@OneToMany(mappedBy="turma")
	public List<Aluno> alunos;
	
	@OneToMany(mappedBy="turma")
	public List<Professor> professores;
	
	
	
}
