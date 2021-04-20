package com.example.androidfinalproject_busbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BusAdapter extends BaseAdapter {

    ArrayList<Bus> busList = new ArrayList<>();
    LayoutInflater layoutInflater;

    public BusAdapter(Context context, ArrayList<Bus> busList){
        this.busList = busList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return busList.size();
    }

    @Override
    public Object getItem(int position) {
        return busList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview_bus, null);

            viewHolder = new ViewHolder();

            viewHolder.lvTvBusName = convertView.findViewById(R.id.lvTvBusName);
            viewHolder.lvTvBusPrice = convertView.findViewById(R.id.lvTvBusPrice);
            viewHolder.imgBus1 = convertView.findViewById(R.id.imgBus1);
            viewHolder.imgPower = convertView.findViewById(R.id.imgPower);
            viewHolder.imgTv = convertView.findViewById(R.id.imgTv);
            viewHolder.imgAC = convertView.findViewById(R.id.imgAC);
            viewHolder.imgRating = convertView.findViewById(R.id.imgRating);
            viewHolder.lvTvBlanck = convertView.findViewById(R.id.lvTvBlank);
            viewHolder.tvRating = convertView.findViewById(R.id.tvRating);
            viewHolder.tv1 = convertView.findViewById(R.id.tv1);
            viewHolder.tv2 = convertView.findViewById(R.id.tv2);
            viewHolder.tv3 = convertView.findViewById(R.id.tv3);
            viewHolder.tv4 = convertView.findViewById(R.id.tv4);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.lvTvBusName.setText(" "+ busList.get(position).busName);
        viewHolder.lvTvBusPrice.setText(String.valueOf("$"+busList.get(position).price));
        viewHolder.tvRating.setText(String.valueOf( "  "+busList.get(position).review + " rating"));
        viewHolder.lvTvBlanck.setText("");
        viewHolder.tv1.setText("");
        viewHolder.tv2.setText("");
        viewHolder.tv3.setText("");
        viewHolder.tv4.setText("");

        int imgBus1id = convertView.getResources().getIdentifier("bus","mipmap",layoutInflater.getContext().getPackageName());
        int imgPowerid = convertView.getResources().getIdentifier("power","mipmap",layoutInflater.getContext().getPackageName());
        int imgTvid = convertView.getResources().getIdentifier("ac","mipmap",layoutInflater.getContext().getPackageName());
        int imgACid = convertView.getResources().getIdentifier("tv","mipmap",layoutInflater.getContext().getPackageName());
        int imgRatingid = convertView.getResources().getIdentifier("rating","mipmap",layoutInflater.getContext().getPackageName());

        viewHolder.imgBus1.setImageResource(imgBus1id);
        viewHolder.imgPower.setImageResource(imgPowerid);
        viewHolder.imgTv.setImageResource(imgTvid);
        viewHolder.imgAC.setImageResource(imgACid);
        viewHolder.imgRating.setImageResource(imgRatingid);

        return convertView;
    }

    private class ViewHolder{
        TextView lvTvBusName;
        TextView lvTvBusPrice;
        ImageView imgBus1;
        ImageView imgPower;
        ImageView imgTv;
        ImageView imgAC;
        ImageView imgRating;
        TextView lvTvBlanck;
        TextView tvRating;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
    }
}
