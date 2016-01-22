package br.com.model;

public class Usuario {

	private long id;

	private String nome;

	private String endereco;

	private String email;

	public Usuario() {
		id = 0;
	}

	public Usuario(long id, String nome, String endereco, String email) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Usuario))
			return false;

		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", endereco=" + endereco+ ", email=" + email + "]";
	}

}
