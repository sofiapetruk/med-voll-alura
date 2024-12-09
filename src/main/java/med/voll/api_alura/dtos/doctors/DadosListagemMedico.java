package med.voll.api_alura.dtos.doctors;

import med.voll.api_alura.models.doctors.DoctorModel;
import med.voll.api_alura.models.doctors.Especialidade;

public record DadosListagemMedico(
        Long idDoctor,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosListagemMedico(DoctorModel doctorModel) {
        this(doctorModel.getIdDoctor(), doctorModel.getNome(), doctorModel.getEmail(), doctorModel.getCrm(), doctorModel.getEspecialidade());
    }
}
