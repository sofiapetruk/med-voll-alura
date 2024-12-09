package med.voll.api_alura.models.doctors;

import jakarta.persistence.*;
import med.voll.api_alura.dtos.doctors.DadosAtualizacaoMedico;
import med.voll.api_alura.dtos.doctors.DadosCadastroMedico;
import med.voll.api_alura.models.Endereco;

import java.io.Serializable;

@Entity
@Table(name = "TB_MEDICO")

public class DoctorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "SEQ_DOCTOR", allocationSize = 1)

    @Column(name = "id_doctor")
    private Long idDoctor;
    private String nome;
    private String email;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING) //Mapeia a especialidade como texto no banco
    private Especialidade especialidade;

    @Embedded //  você mantém a anotação @Embedded para indicar que DadosEndereco é um tipo embutido:
    private Endereco endereco;

    private Boolean ativo;

    //construtores, getters e setters
    public DoctorModel() {
    }

    public DoctorModel(Long idDoctor, Boolean ativo, Endereco endereco, Especialidade especialidade, String crm, String telefone, String nome, String email) {
        this.idDoctor = idDoctor;
        this.ativo = ativo;
        this.endereco = endereco;
        this.especialidade = especialidade;
        this.crm = crm;
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
    }

    public Long getIdDoctor() {
        return idDoctor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public DoctorModel(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }

    public void atualizarInformacao(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }


}
