package com.pim.unip.bitbank.uteis;

import android.content.Context;
import android.content.SharedPreferences;

import com.pim.unip.bitbank.Cliente;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClienteUteis {

    public static void salvarListaEmail(Cliente cliente, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("EMAIL_CLIENTE", 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        List<String> listaEmail = new ArrayList<>();

        if (!mPrefs.getString("CLIENTE_EMAIL", "-1").equals("-1")) {
            try {
                JSONArray array = new JSONArray(mPrefs.getString("CLIENTE_EMAIL", "-1"));

                for (int i = 0; array.length() > i; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    listaEmail.add(obj.getString("email"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        listaEmail.add(cliente.getEmail());

        JSONArray array = new JSONArray();
        JSONObject obj;

        try {
            for (int i = 0; listaEmail.size() > i; i++) {
                obj = new JSONObject();
                obj.put("email", listaEmail.get(i));
                array.put(obj);
            }

            prefsEditor.putString("CLIENTE_EMAIL", array.toString());
            prefsEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void salvarUsuario(Cliente cliente, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("CLIENTE" + cliente.getCpf(), 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString("NOME", cliente.getNome());
        prefsEditor.putString("EMAIL", cliente.getEmail());
        prefsEditor.putString("CPF", cliente.getCpf());
        prefsEditor.putString("SENHA", cliente.getSenha());

        prefsEditor.apply();
    }

    public static void salvarUsuarioLogado(Cliente cliente, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("LOGADO_CLIENTE", 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString("CLIENTE_LOGADO", cliente.getCpf());
        prefsEditor.apply();
    }

    public static String recuperarUsuarioLogado(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("LOGADO_CLIENTE", 0);
        return mPrefs.getString("CLIENTE_LOGADO", "-1");
    }

    public static void limparUsuarioLogado(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("LOGADO_CLIENTE", 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.clear();
        prefsEditor.apply();
    }

    public static String recuperarUsuarioNomeLogado(String cpf, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("CLIENTE" + cpf, 0);
        return mPrefs.getString("NOME", "-1");
    }

    public static Boolean verificarCpf(Cliente cliente, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("CLIENTE" + cliente.getCpf(), 0);
        String cpf = mPrefs.getString("CPF", "-1");

        return !cpf.equals("-1");
    }

    public static Boolean verificarEmail(Cliente cliente, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("EMAIL_CLIENTE", 0);
        String email = mPrefs.getString("CLIENTE_EMAIL", "-1");

        List<String> listaEmail = new ArrayList<>();

        if (!email.equals("-1")) {
            try {
                JSONArray array = new JSONArray(mPrefs.getString("CLIENTE_EMAIL", "-1"));

                for (int i = 0; array.length() > i; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    listaEmail.add(obj.getString("email"));
                }

                for (int i = 0; listaEmail.size() > i; i++) {
                    if (listaEmail.get(i).equals(cliente.getEmail())) {
                        return true;
                    }
                }
                return false;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return !email.equals("-1");
    }

    public static Boolean recuperarLogin(Cliente cliente, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("CLIENTE" + cliente.getCpf(), 0);

        return mPrefs.getString("SENHA", "-1").equals(cliente.getSenha());
    }
}
