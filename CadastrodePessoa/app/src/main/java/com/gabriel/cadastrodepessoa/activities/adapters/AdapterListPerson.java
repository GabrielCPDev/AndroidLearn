package com.gabriel.cadastrodepessoa.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.cadastrodepessoa.R;
import com.gabriel.cadastrodepessoa.entities.Person;

import java.util.List;

public class AdapterListPerson extends RecyclerView.Adapter<AdapterListPerson.PersonViewHolder> {

    private final List<Person> people;
    private Context context;

    public AdapterListPerson(List<Person> people, Context ctx) {
        this.people = people;
        this.context = ctx;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.activity_lista_pessoa, parent, false);
        return new PersonViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = people.get(position);
        holder.binding(person);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder{

        private Person person;
        private TextView fieldName, fieldAge, fieldAdress;


        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            initFields(itemView);
        }

        private void initFields(@NonNull View itemView) {
            fieldName = itemView.findViewById(R.id.textNome);
            fieldAge = itemView.findViewById(R.id.textIdade);
            fieldAdress = itemView.findViewById(R.id.textEndereco);
        }
        public void binding(Person p){
            this.person = p;
            bindingFields(p);
        }
        public void bindingFields(Person p){
            fieldName.setText(p.getName());
            fieldAge.setText(p.getAge().toString());
            fieldAdress.setText(p.getAddress());
        }
    }
}
