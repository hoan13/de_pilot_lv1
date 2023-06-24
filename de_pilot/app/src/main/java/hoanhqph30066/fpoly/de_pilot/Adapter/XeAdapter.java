package hoanhqph30066.fpoly.de_pilot.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hoanhqph30066.fpoly.de_pilot.DAO.XeDAO;
import hoanhqph30066.fpoly.de_pilot.Model.Xe;
import hoanhqph30066.fpoly.de_pilot.R;

public class XeAdapter extends  RecyclerView.Adapter<XeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Xe>list = new ArrayList<>();
    public XeDAO xeDAO;

    public XeAdapter(Context context, ArrayList<Xe> list, XeDAO xeDAO) {
        this.context = context;
        this.list = list;
        this.xeDAO = xeDAO;
    }

    public XeAdapter(Context context) {
        this.context = context;
        xeDAO = new XeDAO(context);
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Xe> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        Xe xe  = list.get(position);

        holder.item_tenxe.setText(xe.getTen());
        holder.item_hangxe.setText(xe.getHangXe());
        holder.item_namsx.setText(String.valueOf(xe.getNamSX()));
        holder.item_giaxe.setText(String.valueOf(xe.getGiaBan()));

        holder.item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Xe : " +xe.getTen());
                builder.setTitle("Bạn có chắc muốn xóa ");
                builder.setCancelable(false);

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XeDAO xeDAO1 = new XeDAO(context);
                        int check = xeDAO1.XoaXe(list.get(holder.getAdapterPosition()).getId());
                        if(check >0){
                            Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                            list = xeDAO1.getAllData();
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_tenxe, item_hangxe, item_namsx, item_giaxe;
        ImageView item_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tenxe = itemView.findViewById(R.id.tvTenXe);
            item_hangxe = itemView.findViewById(R.id.tvHangxe);
            item_namsx = itemView.findViewById(R.id.tvNamSX);
            item_giaxe = itemView.findViewById(R.id.tvGia);
            item_delete = itemView.findViewById(R.id.imgXoaSach);

        }
    }
}
