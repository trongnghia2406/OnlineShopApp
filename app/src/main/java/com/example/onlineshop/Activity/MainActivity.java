package com.example.onlineshop.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.onlineshop.Adapter.CategoryAdapter;
import com.example.onlineshop.R;
import com.example.onlineshop.ViewModel.MainViewModel;
import com.example.onlineshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new MainViewModel();
        initCategory();
    }

    private void initCategory() {
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        viewModel.loadCategory().observeForever(categoryModels -> {
            binding.categoryView.setLayoutManager(new LinearLayoutManager(
                    MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            binding.categoryView.setAdapter(new CategoryAdapter(categoryModels));
            binding.categoryView.setNestedScrollingEnabled(true);
            binding.progressBarCategory.setVisibility(View.GONE);
        });
    }
}