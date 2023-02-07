package ir.webkhoon.pixart.ui.home;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.webkhoon.pixart.databinding.PostRowBinding;
import ir.webkhoon.pixart.model.Post;
import ir.webkhoon.pixart.model.User;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostRowBinding rowBinding = PostRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.imgPost.setImageBitmap(post.bitmap);
        holder.imgProfile.setImageBitmap(post.owner.profile);
        holder.txtUsername.setText(post.owner.username);
        holder.txtLikes.setText(post.likes.size() + " Likes");
        holder.txtDescription.setText(post.description);
        holder.imgPost.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(holder.imgPost.getContext(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (post.likes.contains(User.currentUser)) {
                        post.likes.remove(User.currentUser);
                    } else {
                        post.likes.add(User.currentUser);
                    }
                    holder.txtLikes.setText(post.likes.size() + " Likes");
                    return super.onDoubleTap(e);
                }

                @Override
                public void onLongPress(@NonNull MotionEvent e) {
                    User.currentUser.profile = post.bitmap;
                    notifyDataSetChanged();
                    super.onLongPress(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TEST", "Raw event: " + event.getAction() + ", (" + event.getRawX() + ", " + event.getRawY() + ")");
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProfile, imgPost;
        TextView txtUsername, txtLikes, txtDescription;

        public ViewHolder(PostRowBinding rowBinding) {
            super(rowBinding.getRoot());
            imgProfile = rowBinding.imgProfile;
            imgPost = rowBinding.imgPost;
            txtUsername = rowBinding.txtUsername;
            txtLikes = rowBinding.txtLikes;
            txtDescription = rowBinding.txtDescription;
        }
    }
}
