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
public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        this.pessoasJuridicas = new ArrayList<>();
    }

    // Método inserir
    public void inserir(PessoaJuridica pessoa) {
        pessoasJuridicas.add(pessoa);
    }

    // Método alterar
    public void alterar(PessoaJuridica pessoa) {
        Optional<PessoaJuridica> pessoaExistente = obter(pessoa.getId());
        pessoaExistente.ifPresent(p -> {
            p.setNome(pessoa.getNome());
            p.setCnpj(pessoa.getCnpj());
        });
    }

    // Método excluir
    public void excluir(int id) {
        pessoasJuridicas.removeIf(p -> p.getId() == id);
    }

    // Método obter
    public Optional<PessoaJuridica> obter(int id) {
        return pessoasJuridicas.stream().filter(p -> p.getId() == id).findFirst();
    }

    // Método obterTodos
    public ArrayList<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

    // Método persistir
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasJuridicas);
        }
    }

    // Método recuperar
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}
