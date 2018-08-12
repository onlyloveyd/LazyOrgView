package com.gzgsnet.lazyorgviewsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import cn.onlyloveyd.lazyorgview.adapter.TreeNode;
import cn.onlyloveyd.lazyorgview.widget.LazyOrgConfig;
import cn.onlyloveyd.lazyorgview.widget.LazyOrgView;

/** @author Mraz */
public class MainActivity extends AppCompatActivity {

  LazyOrgView lazyOrgView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      lazyOrgView = findViewById(R.id.rv_content);

      TreeNode root = new TreeNode("贵阳供电局");
      root.isRoot = true;

      TreeNode A = new TreeNode("输电管理所");
      TreeNode Aa = new TreeNode("线路运检一班");
      TreeNode Ab = new TreeNode("线路运检二班");
      TreeNode Ac = new TreeNode("线路运检三班");
      TreeNode Ad = new TreeNode("线路运检四班");
      TreeNode Ae = new TreeNode("线路运检五班");

      TreeNode B = new TreeNode("输电管理所2");
      TreeNode Ba = new TreeNode("线路运检一班1");
      TreeNode Bb = new TreeNode("线路运检二班2");

      root.addChildNode(A);
      root.addChildNode(B);

      A.addChildNode(Aa);
      A.addChildNode(Ab);
      A.addChildNode(Ac);
      A.addChildNode(Ad);
      A.addChildNode(Ae);

      B.addChildNode(Ba);
      B.addChildNode(Bb);


	  LazyOrgConfig lazyOrgConfig = new LazyOrgConfig();
	  lazyOrgConfig.setLineColor(Color.BLUE).setTextSize(18).setLineWidth(1).setTextBgColor(Color.GREEN).setTextBgRes(R.drawable.rect_shape);
	  lazyOrgView.setLazyOrgConfig(lazyOrgConfig);
      lazyOrgView.setRootNode(root);


  }
}
