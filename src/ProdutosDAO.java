package dao;

import dto.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Ariane
 * @version 1.0
 * @since Primeira versão
 */
public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    // Método para cadastrar produto no banco de dados
    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {

        conn = conectaDAO.getConnection(); // Abre a conexão

        try {
            // Prepara o comando SQL de inserção
            String sql = "INSERT INTO Produto (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);

            // Define os valores nos campos do banco de dados
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            // Executa a inserção e retorna o número de linhas afetadas
            int resultado = prep.executeUpdate();

            if (resultado > 0) {
                // Produto foi cadastrado com sucesso
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            } else {
                // Algo deu errado no cadastro
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto. Verifique os dados e tente novamente.");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ProdutosDAO cadastrarProduto: " + erro.getMessage());
        } finally {
            try {
                // Fecha a conexão
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }
    }

    // Método para listar todos os produtos do banco de dados
    public ArrayList<ProdutosDTO> listarProdutos() throws SQLException {
        conn = conectaDAO.getConnection(); // Abre a conexão
        String sql = "SELECT * FROM Produto";

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                // Criar objeto ProdutoDTO para cada registro no banco
                ProdutosDTO produto = new ProdutosDTO();
                produto.setIdProduto(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));

                // Adiciona o produto à lista de produtos
                listagem.add(produto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ProdutosDAO listarProdutos: " + erro.getMessage());
        } finally {
            try {
                // Fecha a conexão
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }

        // Retorna a lista de produtos
        return listagem;
    }

public void venderProduto(int produtoId) throws SQLException {
    conn = conectaDAO.getConnection(); // Abre a conexão
    String sqlVerificar = "SELECT * FROM Produto WHERE id = ?";

    try {
        // Verifica se o produto com o ID informado existe
        prep = conn.prepareStatement(sqlVerificar);
        prep.setInt(1, produtoId);
        resultset = prep.executeQuery();

        if (resultset.next()) {
            // O produto existe, agora podemos marcar como vendido
            String sqlVender = "UPDATE Produto SET status = 'Vendido' WHERE id = ?";
            PreparedStatement prepVender = conn.prepareStatement(sqlVender);
            prepVender.setInt(1, produtoId);
            
            // Executa a atualização do status para 'Vendido'
            int resultado = prepVender.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao vender o produto.");
            }

        } else {
            // Produto não existe, exibe mensagem de erro
            JOptionPane.showMessageDialog(null, "ID inválido. Por favor, selecione um ID válido.");
        }

    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ProdutosDAO venderProduto: " + erro.getMessage());
    } finally {
        try {
            // Fecha a conexão
            if (resultset != null) {
                resultset.close();
            }
            if (prep != null) {
                prep.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
        }
    }
}


    public ArrayList<ProdutosDTO> listarProdutosVendidos() throws SQLException {
        conn = conectaDAO.getConnection(); // Abre a conexão
        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();

        try {
            // SQL para selecionar produtos com status 'vendido'
            String sql = "SELECT * FROM Produto WHERE status = 'vendido'";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setIdProduto(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));

                // Adiciona o produto vendido à lista
                produtosVendidos.add(produto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ProdutosDAO listarProdutosVendidos: " + erro.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (prep != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }

        return produtosVendidos;
    }

   public String obterNomeProduto(int produtoId) throws SQLException {
    conn = conectaDAO.getConnection(); // Abre a conexão
    String sql = "SELECT nome FROM Produto WHERE id = ?";
    String nomeProduto = null;

    try {
        prep = conn.prepareStatement(sql);
        prep.setInt(1, produtoId);
        resultset = prep.executeQuery();

        if (resultset.next()) {
            nomeProduto = resultset.getString("nome"); // Recupera o nome do produto
        }
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao obter nome do produto: " + erro.getMessage());
    } finally {
        try {
            // Fecha a conexão
            if (resultset != null) {
                resultset.close();
            }
            if (prep != null) {
                prep.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
        }
    }

    return nomeProduto; // Retorna o nome do produto ou null se não encontrado
}
}