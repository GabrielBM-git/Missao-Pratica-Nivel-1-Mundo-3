/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gabri
 */
public class CadastroPOO {

    /**
     * @param args the command line arguments
     */
    public static void main_(String[] args) {
        // Testando o repositório de PessoaFisica
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        repo1.inserir(new PessoaFisica(1, "João Silva", "123.456.789-00", 30));
        repo1.inserir(new PessoaFisica(2, "Maria Souza", "987.654.321-00", 25));
        System.out.println("Dados de Pessoas Físicas Armazenados.");

        // Persistindo os dados em um arquivo
        String nomeArquivoPessoaFisica = "pessoas_fisicas.dat";
        try {
            repo1.persistir(nomeArquivoPessoaFisica);
        } catch (IOException e) {
            System.err.println("Erro ao persistir pessoas físicas: " + e.getMessage());
        }

        // Recuperando os dados do arquivo em um novo repositório
        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        try {
            repo2.recuperar(nomeArquivoPessoaFisica);
            System.out.println("Dados de Pessoa Física Recuperados:");
            for (PessoaFisica pf : repo2.obterTodos()) {
                pf.exibir();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar pessoas físicas: " + e.getMessage());
        }

        // Testando o repositório de PessoaJuridica
        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        repo3.inserir(new PessoaJuridica(1, "Empresa A", "12.345.678/0001-90"));
        repo3.inserir(new PessoaJuridica(2, "Empresa B", "98.765.432/0001-99"));
        System.out.println("Dados de Pessoas Juridicas Armazenados.");

        // Persistindo os dados em um arquivo
        String nomeArquivoPessoaJuridica = "pessoas_juridicas.dat";
        try {
            repo3.persistir(nomeArquivoPessoaJuridica);
        } catch (IOException e) {
            System.err.println("Erro ao persistir pessoas jurídicas: " + e.getMessage());
        }

        // Recuperando os dados do arquivo em um novo repositório
        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        try {
            repo4.recuperar(nomeArquivoPessoaJuridica);
            System.out.println("Dados de Pessoas Jurídicas Recuperados:");
            for (PessoaJuridica pj : repo4.obterTodos()) {
                pj.exibir();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar pessoas jurídicas: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            // Apresentar o menu de opções
            System.out.println("\nCadastro em Modo Texto:");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir por ID");
            System.out.println("5. Exibir todos");
            System.out.println("6. Salvar dados");
            System.out.println("7. Recuperar dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a linha restante

            switch (opcao) {
                case 1:
                    // Incluir Pessoa
                    System.out.println("Escolha o tipo: 1 para Física, 2 para Jurídica:");
                    int tipo = scanner.nextInt();
                    scanner.nextLine(); // Consumir a linha restante
                    if (tipo == 1) {
                        System.out.print("Digite o ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Digite a idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine();
                        pessoaFisicaRepo.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo == 2) {
                        System.out.print("Digite o ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o CNPJ: ");
                        String cnpj = scanner.nextLine();
                        pessoaJuridicaRepo.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                    break;

                case 2:
                    // Alterar Pessoa
                    System.out.println("Escolha o tipo: 1 para Física, 2 para Jurídica:");
                    tipo = scanner.nextInt();
                    scanner.nextLine();
                    if (tipo == 1) {
                        System.out.print("Digite o ID da pessoa física a alterar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        var pessoaFisica = pessoaFisicaRepo.obter(id);
                        if (pessoaFisica.isPresent()) {
                            PessoaFisica pf = pessoaFisica.get();
                            System.out.println("Dados atuais:");
                            pf.exibir();
                            System.out.print("Digite o novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Digite o novo CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Digite a nova idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine();
                            pf.setNome(nome);
                            pf.setCpf(cpf);
                            pf.setIdade(idade);
                            pessoaFisicaRepo.alterar(pf);
                        } else {
                            System.out.println("Pessoa física não encontrada!");
                        }
                    } else if (tipo == 2) {
                        System.out.print("Digite o ID da pessoa jurídica a alterar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        var pessoaJuridica = pessoaJuridicaRepo.obter(id);
                        if (pessoaJuridica.isPresent()) {
                            PessoaJuridica pj = pessoaJuridica.get();
                            System.out.println("Dados atuais:");
                            pj.exibir();
                            System.out.print("Digite o novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Digite o novo CNPJ: ");
                            String cnpj = scanner.nextLine();
                            pj.setNome(nome);
                            pj.setCnpj(cnpj);
                            pessoaJuridicaRepo.alterar(pj);
                        } else {
                            System.out.println("Pessoa jurídica não encontrada!");
                        }
                    }
                    break;

                case 3:
                    // Excluir Pessoa
                    System.out.println("Escolha o tipo: 1 para Física, 2 para Jurídica:");
                    tipo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o ID: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();
                    if (tipo == 1) {
                        pessoaFisicaRepo.excluir(idExcluir);
                    } else if (tipo == 2) {
                        pessoaJuridicaRepo.excluir(idExcluir);
                    }
                    break;

                case 4:
                    // Exibir por ID
                    System.out.println("Escolha o tipo: 1 para Física, 2 para Jurídica:");
                    tipo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o ID: ");
                    int idExibir = scanner.nextInt();
                    scanner.nextLine();
                    if (tipo == 1) {
                        var pessoaFisica = pessoaFisicaRepo.obter(idExibir);
                        pessoaFisica.ifPresentOrElse(PessoaFisica::exibir,
                                () -> System.out.println("Pessoa física não encontrada!"));
                    } else if (tipo == 2) {
                        var pessoaJuridica = pessoaJuridicaRepo.obter(idExibir);
                        pessoaJuridica.ifPresentOrElse(PessoaJuridica::exibir,
                                () -> System.out.println("Pessoa jurídica não encontrada!"));
                    }
                    break;

                case 5:
                    // Exibir todos
                    System.out.println("Escolha o tipo: 1 para Física, 2 para Jurídica:");
                    tipo = scanner.nextInt();
                    scanner.nextLine();
                    if (tipo == 1) {
                        pessoaFisicaRepo.obterTodos().forEach(PessoaFisica::exibir);
                    } else if (tipo == 2) {
                        pessoaJuridicaRepo.obterTodos().forEach(PessoaJuridica::exibir);
                    }
                    break;

                case 6:
                    // Salvar dados
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoSalvar = scanner.nextLine();
                    try {
                        pessoaFisicaRepo.persistir(prefixoSalvar + ".fisica.bin");
                        pessoaJuridicaRepo.persistir(prefixoSalvar + ".juridica.bin");
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar os dados: " + e.getMessage());
                    }
                    break;

                case 7:
                    // Recuperar dados
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoRecuperar = scanner.nextLine();
                    try {
                        pessoaFisicaRepo.recuperar(prefixoRecuperar + ".fisica.bin");
                        pessoaJuridicaRepo.recuperar(prefixoRecuperar + ".juridica.bin");
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Erro ao recuperar os dados: " + e.getMessage());
                    }
                    break;

                case 0:
                    // Finalizar o sistema
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}
