package med.voll.api_alura.dtos.doctors;

import jakarta.validation.constraints.NotNull;
import med.voll.api_alura.dtos.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
