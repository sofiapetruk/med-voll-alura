package med.voll.api_alura.dtos.doctors;

import med.voll.api_alura.models.doctors.DoctorModel;
import med.voll.api_alura.models.Endereco;
import med.voll.api_alura.models.doctors.Especialidade;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedico (DoctorModel doctor){
        this(doctor.getIdDoctor(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getTelefone(), doctor.getEspecialidade(), doctor.getEndereco());
    }
}
