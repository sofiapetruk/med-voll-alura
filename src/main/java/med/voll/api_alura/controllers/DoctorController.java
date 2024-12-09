package med.voll.api_alura.controllers;

import jakarta.validation.Valid;
import med.voll.api_alura.dtos.doctors.DadosAtualizacaoMedico;
import med.voll.api_alura.dtos.doctors.DadosDetalhamentoMedico;
import med.voll.api_alura.dtos.doctors.DadosListagemMedico;
import med.voll.api_alura.dtos.doctors.DadosCadastroMedico;
import med.voll.api_alura.models.doctors.DoctorModel;
import med.voll.api_alura.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Optional;


@RestController
@RequestMapping("medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> saveDoctor(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico,UriComponentsBuilder uriBuilder
    ) {
        // Criação do objeto doctorModel a partir dos dados recebidos
        var doctorModel = new DoctorModel(dadosCadastroMedico);

        // Salvando o modelo no banco de dados
        doctorRepository.save(doctorModel);

        // Gerando o URI do novo recurso (médico)
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctorModel.getIdDoctor()).toUri();

        // Retornando a resposta com status 201 (Created) e os detalhes do médico
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(doctorModel));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> getAllDoctors(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {

        // Aqui, o método findAll retorna uma Page, que já tem suporte à paginação
        Page<DoctorModel> doctorPage = doctorRepository.findAllByAtivoTrue(pageable);

        // Convertendo de DoctorModel para DadosListagemMedico
        Page<DadosListagemMedico> doctorList = doctorPage.map(DadosListagemMedico::new);

        return ResponseEntity.ok(doctorList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var doctor = doctorRepository.getReferenceById(dados.id());
        doctor.atualizarInformacao(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteDoctor(@PathVariable(value = "id") Long idDoctor) {
        // Buscar o médico pelo ID
        Optional<DoctorModel> doctorO = doctorRepository.findById(idDoctor);

        // Caso não encontrado, retornar 404
        if (doctorO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        // Alterar o status do médico para inativo
        var doctorModel = doctorO.get();
        doctorModel.excluir(); // Atualiza o atributo ativo para false (inativo)

        // Salvar as alterações
        doctorRepository.save(doctorModel);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long idDoctor) {
        var doctor = doctorRepository.getReferenceById(idDoctor);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(doctor));
    }


}
