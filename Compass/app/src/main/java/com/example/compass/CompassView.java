package com.example.compass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {

    private Paint paintCircle;
    private Paint paintInsideCircle;
    private Paint paintArrow;
    private Paint paintText;
    private Paint paintCenterText;

    private float direction = 0f; // 当前方向（角度）

    public CompassView(Context context) {
        super(context);
        init();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 内圈画笔
        paintInsideCircle = new Paint();
        paintInsideCircle.setColor(Color.parseColor("#420001"));
        paintInsideCircle.setStyle(Paint.Style.STROKE);
        paintInsideCircle.setStrokeWidth(5f);
        paintInsideCircle.setAntiAlias(true);

        // 箭头画笔
        paintArrow = new Paint();
        paintArrow.setColor(Color.parseColor("#e30200"));
        paintArrow.setStyle(Paint.Style.FILL_AND_STROKE);
        paintArrow.setAntiAlias(true);

        // 方向文字画笔
        paintText = new Paint();
        paintText.setColor(Color.parseColor("#420001"));
        paintText.setTextSize(50f);
        paintText.setTextAlign(Paint.Align.CENTER);

        // 圆心文字画笔
        paintCenterText = new Paint();
        paintCenterText.setColor(Color.parseColor("#e30200"));
        paintCenterText.setTextSize(50f);
        paintCenterText.setTextAlign(Paint.Align.CENTER);
        paintCenterText.setAntiAlias(true);
    }

    public void setDirection(float direction) {
        this.direction = direction;
        invalidate(); // 触发重绘
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(centerX, centerY) - 20;
        int insideRadius = Math.min(centerX, centerY) - 120;

        // 绘制内圈
        canvas.drawCircle(centerX, centerY, insideRadius, paintInsideCircle);

        // 绘制指向北的红色箭头
        canvas.save();
        canvas.rotate(direction, centerX, centerY);

        Path arrowPath = new Path();
        float arrowSize = radius * 0.7f; // 指针长度
        float startOffset = 120f; // 指针起始偏移
        arrowPath.moveTo(centerX, centerY - arrowSize); // 顶点
        arrowPath.lineTo(centerX - 15, centerY - startOffset); // 左下
        arrowPath.lineTo(centerX + 15, centerY - startOffset); // 右下
        arrowPath.close(); // 闭合路径

        canvas.drawPath(arrowPath, paintArrow);
        canvas.restore();

        // 绘制方向文本
        canvas.drawText("N", centerX, centerY - radius + 50, paintText);
        canvas.drawText("S", centerX, centerY + radius - 20, paintText);
        canvas.drawText("E", centerX + radius - 40, centerY + 15, paintText);
        canvas.drawText("W", centerX - radius + 40, centerY + 15, paintText);

        // 绘制圆心的方向信息
        String compassData = getCompassData(direction);
        canvas.drawText(compassData, centerX, centerY + 20, paintCenterText);
    }

    /**
     * 根据方向角计算指南针数据（如 87°E 或 59°NE）
     */
    private String getCompassData(float direction) {
        direction = (direction + 360) % 360; // 保证方向为正数
        String cardinalDirection;

        if (direction >= 337.5 || direction < 22.5) {
            cardinalDirection = "N";
        } else if (direction >= 22.5 && direction < 67.5) {
            cardinalDirection = "NE";
        } else if (direction >= 67.5 && direction < 112.5) {
            cardinalDirection = "E";
        } else if (direction >= 112.5 && direction < 157.5) {
            cardinalDirection = "SE";
        } else if (direction >= 157.5 && direction < 202.5) {
            cardinalDirection = "S";
        } else if (direction >= 202.5 && direction < 247.5) {
            cardinalDirection = "SW";
        } else if (direction >= 247.5 && direction < 292.5) {
            cardinalDirection = "W";
        } else {
            cardinalDirection = "NW";
        }

        // 返回格式化的字符串
        return String.format("%.0f° %s", direction, cardinalDirection);
    }
}
