package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class SignUp {

  private static String nomeArquivo = "accounts.ser";
  private static List<User> cadastrados = carregarCadastro();

  public static List<User> getCadastrados() {
    if (cadastrados == null)
      cadastrados = carregarCadastro();
    return cadastrados;
  }

  private static List<User> carregarCadastro() {
    List<User> cadastro = new ArrayList<User>();
    try {
      File f = new File(nomeArquivo);
      if (!f.exists())
        return cadastro;
      BufferedReader csvReader = new BufferedReader(new FileReader(nomeArquivo));
      String row;
      while ((row = csvReader.readLine()) != null) {
        String[] dados = row.split(",");
        cadastro.add(new User(dados[0], dados[1], dados[2]));
      }
      csvReader.close();

    } catch (IOException ex) {
      System.out.println("IOException lançada");
    }
    return cadastro;
  }

  public static void salvarCadastro() {
    try {
      File f = new File(nomeArquivo);
      if (!f.exists())
        f.createNewFile();
      FileWriter csvWriter = new FileWriter(nomeArquivo);
      for (User u : cadastrados) {
        csvWriter.append(u.getNome() + ",");
        csvWriter.append(u.getEmail() + ",");
        csvWriter.append(u.getSenha() + "\n");
      }
      csvWriter.close();
    } catch (IOException ex) {
      System.out.println("IOException lançada.");
      ex.printStackTrace();
    }
  }

  public static void cadastrarNovo(String nome, String email, String senha) {
    if (cadastrados == null)
      cadastrados = carregarCadastro();

    String key = PasswordUtil.hashPassword(senha, PasswordUtil.getAlgorithm()).get();
    
    cadastrados.add(new User(nome, email, key));
    salvarCadastro();
  }

}
