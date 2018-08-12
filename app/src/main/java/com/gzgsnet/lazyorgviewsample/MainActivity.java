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

      TreeNode root = new TreeNode("总经理");
      root.isRoot = true;

      TreeNode A = new TreeNode("副经理");
      TreeNode Aa = new TreeNode("行政部");
      TreeNode Ab = new TreeNode("财务部");
      TreeNode Ac = new TreeNode("采购部");

      TreeNode B = new TreeNode("副经理");
      TreeNode Ba = new TreeNode("工程部");
      TreeNode Bb = new TreeNode("生产部");

	  TreeNode C = new TreeNode("副经理");
	  TreeNode Ca = new TreeNode("营销部");
	  TreeNode Cb = new TreeNode("方案造价部");
	  TreeNode Cc = new TreeNode("售后服务部");

      root.addChildNode(A);
      root.addChildNode(B);
      root.addChildNode(C);

      A.addChildNode(Aa);
      A.addChildNode(Ab);
      A.addChildNode(Ac);

      B.addChildNode(Ba);
      B.addChildNode(Bb);

	  C.addChildNode(Ca);
	  C.addChildNode(Cb);

	  lazyOrgView.setRootNode(root);
	  LazyOrgConfig lazyOrgConfig = new LazyOrgConfig();
	  lazyOrgConfig.setLineColor(Color.BLACK).setTextSize(12).setLineWidth(4).setTextBgRes(R.drawable.corner_shape);
	  lazyOrgView.setLazyOrgConfig(lazyOrgConfig);
  }
}
