package com.socks.androiddemo.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.socks.androiddemo.R;

public class MatrixActivity extends Activity implements OnTouchListener {

	private static final String TAG = "TransformMatrixActivity";

	private TransformMatrixView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		view = new TransformMatrixView(this);
		view.setScaleType(ImageView.ScaleType.MATRIX);
		view.setOnTouchListener(this);

		setContentView(view);
	}

	class TransformMatrixView extends ImageView {
		private Bitmap bitmap;
		private Matrix matrix;

		public TransformMatrixView(Context context) {
			super(context);
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sophie);
			matrix = new Matrix();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			// 画出原图像
			canvas.drawBitmap(bitmap, 0, 0, null);
			// 画出变换后的图像
			canvas.drawBitmap(bitmap, matrix, null);
		}

		@Override
		public void setImageMatrix(Matrix matrix) {
			this.matrix.set(matrix);
			super.setImageMatrix(matrix);
		}

		public Bitmap getImageBitmap() {
			return bitmap;
		}
	}

	public boolean onTouch(View v, MotionEvent e) {
		if (e.getAction() == MotionEvent.ACTION_UP) {
			Matrix matrix = new Matrix();

//			// 1. 平移
//			matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
//			// 在x方向平移view.getImageBitmap().getWidth()，在y轴方向view.getImageBitmap().getHeight()
//			view.setImageMatrix(matrix);

//			// 2. 旋转(围绕图像的中心点)
//			matrix.setRotate(45f, view.getImageBitmap().getWidth() / 2f, view.getImageBitmap().getHeight() / 2f);
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate(view.getImageBitmap().getWidth() * 1.5f, 0f);
//			view.setImageMatrix(matrix);


			// 3. 旋转(围绕坐标原点) + 平移(效果同2)
//			matrix.setRotate(45f);
//			matrix.preTranslate(-1f * view.getImageBitmap().getWidth() / 2f, -1f * view.getImageBitmap().getHeight() / 2f);
//			matrix.postTranslate((float)view.getImageBitmap().getWidth() / 2f, (float)view.getImageBitmap().getHeight() / 2f);
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate((float)view.getImageBitmap().getWidth() * 1.5f, 0f);
//			view.setImageMatrix(matrix);


			// 4. 缩放
//			matrix.setScale(2f, 2f);
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
//			view.setImageMatrix(matrix);


			// 5. 错切 - 水平
//			matrix.setSkew(1f, 0f);
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate(view.getImageBitmap().getWidth(), 0f);
//			view.setImageMatrix(matrix);


//			// 6. 错切 - 垂直
//			matrix.setSkew(0f, 0.5f);
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//			view.setImageMatrix(matrix);


//			7. 错切 - 水平 + 垂直
//			matrix.setSkew(0.5f, 0.5f);
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//			view.setImageMatrix(matrix);

//			// 8. 对称 (水平对称)
			float matrix_values[] = {1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
			matrix.setValues(matrix_values);
			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
			matrix.postTranslate(0f, view.getImageBitmap().getHeight() * 2f);
			view.setImageMatrix(matrix);

//			// 9. 对称 - 垂直
//			float matrix_values[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
//			matrix.setValues(matrix_values);
//
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate(view.getImageBitmap().getWidth() * 2f, 0f);
//			view.setImageMatrix(matrix);

//			// 10. 对称(对称轴为直线y = x)
//			float matrix_values[] = {0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f};
//			matrix.setValues(matrix_values);
//
//			// 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//			matrix.postTranslate(view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth(),
//					view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth());
//			view.setImageMatrix(matrix);

			// 下面的代码是为了查看matrix中的元素
			float[] matrixValues = new float[9];
			matrix.getValues(matrixValues);
			for(int i = 0; i < 3; ++i)
			{
				String temp = new String();
				for(int j = 0; j < 3; ++j)
				{
					temp += matrixValues[3 * i + j ] + "\t";
				}
				Log.e(TAG, temp);
			}

			view.invalidate();
		}
		return true;
	}
}

//下面给出上述代码中，各种变换的具体结果及其对应的相关变换矩阵
//1.平移
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321712352qQRu.gif"alt="">
//输出的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217123565Wwz.gif"alt="">
//请对照第一部分中的“一、平移变换”所讲的情形，考察上述矩阵的正确性。
//
//2.旋转(围绕图像的中心点)
//<img src="http://hi.csdn.net/attachment/201111/19/0_132171250556xp.gif"alt="">
//输出的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321712512Yj1i.gif"alt="">
//它实际上是
//matrix.setRotate(45f,view.getImageBitmap().getWidth()/2f,view.getImageBitmap().getHeight()/2f);
//matrix.postTranslate(view.getImageBitmap().getWidth()*1.5f,0f);
//这两条语句综合作用的结果。根据第一部分中“二、旋转变换”里面关于围绕某点旋转的公式，
//matrix.setRotate(45f,view.getImageBitmap().getWidth()/2f,view.getImageBitmap().getHeight()/2f);
//所产生的转换矩阵就是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321712644I54M.gif"alt="">
//而matrix.postTranslate(view.getImageBitmap().getWidth()*1.5f,0f);的意思就是在上述矩阵的左边再乘以下面的矩阵：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217126508k4V.gif"alt="">
//关于post是左乘这一点，我们在前面的理论部分曾经提及过，后面我们还会专门讨论这个问题。
//
//所以它实际上就是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217126608wdT.gif"alt="">
//出去计算上的精度误差，我们可以看到我们计算出来的结果，和程序直接输出的结果是一致的。
//
//3.旋转(围绕坐标原点旋转，在加上两次平移，效果同2)
//<img src="http://hi.csdn.net/attachment/201111/19/0_132171250556xp.gif"alt="">
//根据第一部分中“二、旋转变换”里面关于围绕某点旋转的解释，不难知道：
//matrix.setRotate(45f,view.getImageBitmap().getWidth()/2f,view.getImageBitmap().getHeight()/2f);
//等价于
//matrix.setRotate(45f);
//matrix.preTranslate(-1f*view.getImageBitmap().getWidth()/2f,-1f*view.getImageBitmap().getHeight()/2f);
//matrix.postTranslate((<strong>float</strong>)view.getImageBitmap().getWidth()/2f,(<strong>float</strong>)view.getImageBitmap().getHeight()/2f);
//
//其中matrix.setRotate(45f)对应的矩阵是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321712949GjN7.gif"alt="">
//matrix.preTranslate(-1f*view.getImageBitmap().getWidth()/2f,-1f*view.getImageBitmap().getHeight()/2f)对应的矩阵是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321712956BNj8.gif"alt="">
//由于是preTranslate，是先乘，也就是右乘，即它应该出现在matrix.setRotate(45f)所对应矩阵的右侧。
//
//matrix.postTranslate((<strong>float</strong>)view.getImageBitmap().getWidth()/2f,(<strong>float</strong>)view.getImageBitmap().getHeight()/2f)对应的矩阵是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321712963iNO1.gif"alt="">
//这次由于是postTranslate，是后乘，也就是左乘，即它应该出现在matrix.setRotate(45f)所对应矩阵的左侧。
//
//所以综合起来，
//matrix.setRotate(45f);
//matrix.preTranslate(-1f*view.getImageBitmap().getWidth()/2f,-1f*view.getImageBitmap().getHeight()/2f);
//matrix.postTranslate((<strong>float</strong>)view.getImageBitmap().getWidth()/2f,(<strong>float</strong>)view.getImageBitmap().getHeight()/2f);
//对应的矩阵就是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713055HOOt.gif"alt="">
//这和下面这个矩阵(围绕图像中心顺时针旋转45度)其实是一样的：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713100VIOz.gif"alt="">
//因此，此处变换后的图像和2中变换后的图像时一样的。
//
//4.缩放变换
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713185yKS7.gif"alt="">
//程序所输出的两个矩阵分别是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217131941R24.gif"alt="">
//其中第二个矩阵，其实是下面两个矩阵相乘的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713201VRxs.gif"alt="">
//大家可以对照第一部分中的“三、缩放变换”和“一、平移变换”说法，自行验证结果。
//
//5.错切变换(水平错切)
//<img src="http://hi.csdn.net/attachment/201111/19/0_132171330766G0.gif"alt="">
//代码所输出的两个矩阵分别是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713314Dk69.gif"alt="">
//其中，第二个矩阵其实是下面两个矩阵相乘的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713322PeML.gif"alt="">
//大家可以对照第一部分中的“四、错切变换”和“一、平移变换”的相关说法，自行验证结果。
//
//6.错切变换(垂直错切)
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713502Akg2.gif"alt="">
//代码所输出的两个矩阵分别是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713509Hz7p.gif"alt="">
//其中，第二个矩阵其实是下面两个矩阵相乘的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713516TUvx.gif"alt="">
//大家可以对照第一部分中的“四、错切变换”和“一、平移变换”的相关说法，自行验证结果。
//
//7.错切变换(水平+垂直错切)
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321713655Qsij.gif"alt="">
//代码所输出的两个矩阵分别是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217136824kKR.gif"alt="">
//其中，后者是下面两个矩阵相乘的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217136931vl4.gif"alt="">
//大家可以对照第一部分中的“四、错切变换”和“一、平移变换”的相关说法，自行验证结果。
//
//8.对称变换(水平对称)
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217141232rJI.gif"alt="">
//代码所输出的两个各矩阵分别是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321714133P0ha.gif"alt="">
//其中，后者是下面两个矩阵相乘的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217141417Bi3.gif"alt="">
//大家可以对照第一部分中的“五、对称变换”和“一、平移变换”的相关说法，自行验证结果。
//
//9.对称变换(垂直对称)
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321714261NN3V.gif"alt="">
//代码所输出的两个矩阵分别是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321714269Kqs4.gif"alt="">
//其中，后者是下面两个矩阵相乘的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321714276ai2f.gif"alt="">
//大家可以对照第一部分中的“五、对称变换”和“一、平移变换”的相关说法，自行验证结果。
//
//10.对称变换(对称轴为直线<em>y=x</em>)
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217144950030.gif"alt="">
//代码所输出的两个矩阵分别是：
//<img src="http://hi.csdn.net/attachment/201111/19/0_13217145038hN8.gif"alt="">
//其中，后者是下面两个矩阵相乘的结果：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321714509sBN4.gif"alt="">
//大家可以对照第一部分中的“五、对称变换”和“一、平移变换”的相关说法，自行验证结果。
//
//11.关于先乘和后乘的问题
//由于矩阵的乘法运算不满足交换律，我们在前面曾经多次提及先乘、后乘的问题，即先乘就是矩阵运算中右乘，后乘就是矩阵运算中的左乘。其实先乘、后乘的概念是针对变换操作的时间先后而言的，左乘、右乘是针对矩阵运算的左右位置而言的。以第一部分“二、旋转变换”中围绕某点旋转的情况为例：
//<img src="http://hi.csdn.net/attachment/201111/19/0_1321714645396z.gif"alt="">
//越靠近原图像中像素的矩阵，越先乘，越远离原图像中像素的矩阵，越后乘。事实上，图像处理时，矩阵的运算是从右边往左边方向进行运算的。这就形成了越在右边的矩阵(右乘)，越先运算(先乘)，反之亦然。
//
//当然，在实际中，如果首先指定了一个matrix，比如我们先setRotate(<img src="http://hi.csdn.net/attachment/201111/19/0_1321714654C9Xc.gif"alt="">)，即指定了上面变换矩阵中，中间的那个矩阵，那么后续的矩阵到底是pre还是post运算，都是相对这个中间矩阵而言的。
//
//<span style="font-size:13px">所有这些，其实都是很自然的事情。</span>