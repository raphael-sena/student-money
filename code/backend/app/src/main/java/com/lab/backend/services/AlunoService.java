package com.lab.backend.services;

import com.lab.backend.entities.Aluno;
import com.lab.backend.entities.Endereco;
import com.lab.backend.entities.dtos.*;
import com.lab.backend.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;
import com.lab.backend.repositories.AlunoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;


    public AlunoService(AlunoRepository alunoRepository, EnderecoRepository enderecoRepository) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public AlunoResponseDTO create(AlunoCreateRequestDTO obj) {

        alunoRepository.findByLogin(obj.pessoa().usuario().login())
                .ifPresent(aluno -> {
                    throw new RuntimeException("Login já existe");
                });

        Aluno aluno = new Aluno();

        aluno.setNome(obj.pessoa().usuario().nome());
        aluno.setLogin(obj.pessoa().usuario().login());
        aluno.setSenha(obj.pessoa().usuario().senha());

        alunoRepository.save(aluno);

        Endereco endereco = new Endereco();
        endereco.setRua(obj.pessoa().usuario().endereco().rua());
        endereco.setNumero(obj.pessoa().usuario().endereco().numero());
        endereco.setComplemento(obj.pessoa().usuario().endereco().complemento());
        endereco.setBairro(obj.pessoa().usuario().endereco().bairro());
        endereco.setCidade(obj.pessoa().usuario().endereco().cidade());
        endereco.setEstado(obj.pessoa().usuario().endereco().estado());
        endereco.setCep(obj.pessoa().usuario().endereco().cep());
        endereco.setUsuario(aluno);

        enderecoRepository.save(endereco);

        aluno.setEndereco(endereco);

        aluno.setCpf(obj.pessoa().cpf());

        aluno.setRg(obj.rg());
        aluno.setCurso(obj.curso());


        return new AlunoResponseDTO(
                new PessoaResponseDTO(
                        new UsuarioResponseDTO(
                                aluno.getId(),
                                aluno.getNome(),
                                aluno.getLogin(),
                                aluno.getSenha(),
                                new EnderecoResponseDTO(
                                        aluno.getEndereco().getId(),
                                        aluno.getEndereco().getRua(),
                                        aluno.getEndereco().getNumero(),
                                        aluno.getEndereco().getComplemento(),
                                        aluno.getEndereco().getBairro(),
                                        aluno.getEndereco().getCidade(),
                                        aluno.getEndereco().getEstado(),
                                        aluno.getEndereco().getCep()
                                )
                        ),
                        aluno.getCpf()
                ),
                aluno.getRg(),
                aluno.getCurso()
        );
    }


}
