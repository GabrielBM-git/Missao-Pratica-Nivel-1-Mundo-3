/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author gabri
 */
public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        this.pessoasFisicas = new ArrayList<>();
    }

    // Método inserir
    public void inserir(PessoaFisica pessoa) {
        pessoasFisicas.add(pessoa);
    }

    // Método alterar
    public void alterar(PessoaFisica pessoa) {
        Optional<PessoaFisica> pessoaExistente = obter(pessoa.getId());
        pessoaExistente.ifPresent(p -> {
            p.setNome(pessoa.getNome());
            p.setCpf(pessoa.getCpf());
            p.setIdade(pessoa.getIdade());
        });
    }

    // Método excluir
    public void excluir(int id) {
        pessoasFisicas.removeIf(p -> p.getId() == id);
    }

    // Método obter
    public Optional<PessoaFisica> obter(int id) {
        return pessoasFisicas.stream().filter(p -> p.getId() == id).findFirst();
    }

    // Método obterTodos
    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    // Método persistir
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasFisicas);
        }
    }

    // Método recuperar
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }
}
