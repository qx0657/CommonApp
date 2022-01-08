package fun.qianxiao.commonapp.activity.opensourcelicense.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fun.qianxiao.commonapp.R;
import fun.qianxiao.commonapp.activity.opensourcelicense.bean.OpenSourceLicense;
import fun.qianxiao.commonapp.base.BaseRecycleViewHolder;
import fun.qianxiao.commonapp.databinding.ItemOpensourcelicenseBinding;


/**
 * @author QianXiao
 */
public class OpenSourceLicenseAdapter extends RecyclerView.Adapter<OpenSourceLicenseAdapter.ViewHolder> {
    private Context context;
    private List<OpenSourceLicense> openSourceLicenses;

    public OpenSourceLicenseAdapter(Context context, List<OpenSourceLicense> openSourceLicenses) {
        this.context = context;
        this.openSourceLicenses = openSourceLicenses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opensourcelicense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OpenSourceLicense openSourceLicense = openSourceLicenses.get(position);
        holder.binding.tvNameItemOsl.setText(openSourceLicense.getName());
        holder.binding.tvAuthorItemOsl.setText(openSourceLicense.getAnthor());
        holder.binding.tvDescribeItemOsl.setText(openSourceLicense.getDescribe());
        holder.binding.tvLicenseItemOsl.setText(openSourceLicense.getLicense());
        holder.binding.tvGoItemOsl.setOnClickListener(view -> context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(openSourceLicense.getUrl()))));
        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(openSourceLicense.getUrl()))));
    }

    @Override
    public int getItemCount() {
        return openSourceLicenses.size();
    }

    class ViewHolder extends BaseRecycleViewHolder<ItemOpensourcelicenseBinding> {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
