package controllers;

import java.util.List;

import models.Aluno;
import models.Disciplina;
import models.Professor;
import models.Turma;
import models.Usuario;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Controller;

public class Alunos extends Controller{
	
	public static void portal_aluno(Aluno aluno) {
		render(aluno);
    }
	
	public static void cadastroDisciplinaAluno(){
		List<Disciplina> disciplinas = Disciplina.findAll();
		List<Aluno> alunos = Aluno.findAll();
		render(disciplinas,alunos);
	}
	
	public static void cadastro_aluno(){
		List<Turma> turmas = Turma.findAll();
		List<Disciplina> disciplinas = Disciplina.findAll();
		render(turmas, disciplinas);
	}
	
	
	public static void salvarAluno(Aluno aluno) {
    	if(aluno.save() != null) {
    		flash.success("Matrícula efetuada com sucesso");
    		listarAluno();
    	}else {
    		flash.error("Houve um erro, tente novamente");
    		listarAluno();
    	}
    }
	
	public static void save(Aluno aluno) {
		if(aluno.save() != null) {
    		flash.success("Aluno salvo com sucesso!");
    		listarAlunoDisciplina();
    	}else {
    		flash.error("Aluno não foi salvo, tente novamente");
    		listarAlunoDisciplina();
    	}
	}
	
		public static void listarAlunoDisciplina() {
			List<Aluno> alunos = Aluno.findAll();
			render(alunos);
		}
	
	 	public static void listarAluno(){
	    	List<Aluno> alunos = Aluno.findAll();
	    	render(alunos);
	     } 
	    
	 
	 	public static List<Aluno> getListaAluno(){
	    	List<Aluno> alunos = Aluno.findAll();
	    	return alunos;
	    }
	    
	  
	    
	    public static Aluno buscaAlunoBD(String matricula, String senha) {
	    	return Aluno.find("matricula = ? and senha = ?", matricula, senha).first();
	    }
	    
	    public static void pesquisarProfessor(String busca) {
	    	List<Professor> professor = Professor.find("nome like ? or matricula like ?", "%" + busca + "%", "%" + busca + "%").fetch();
	    	if(professor.isEmpty()) {
	    		flash.error("Professor não encontrado!");
	    		renderTemplate("Alunos/portal_aluno.html");
	    	}else {
	    		renderTemplate("Alunos/pesquisa.html", professor);
	    	}
	    }
	    
	    public static void remover(long id) {
	    	Aluno alunos = Aluno.findById(id);
	    	if(alunos.delete() != null) {
	    		flash.success("Aluno removido com sucesso!");
	    		listarAluno();
	    	}else {
	    		flash.error("Aluno não foi removido!");
	    		listarAluno();
	    	}
	    }
	    
		public static void editarAluno(Long id) {
			Aluno aluno = Aluno.findById(id);
			List<Turma> turmas = Turma.findAll();
			List<Disciplina> disciplinas = Disciplina.findAll();
			renderTemplate("Alunos/cadastro_aluno.html",aluno, turmas, disciplinas);
		}
		
		public static void main_portal_aluno(Aluno aluno) {
			render(aluno);
		}
}