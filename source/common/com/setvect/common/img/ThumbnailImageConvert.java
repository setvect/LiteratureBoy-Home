package com.setvect.common.img;

import java.awt.image.IndexColorModel;
import java.awt.image.renderable.ParameterBlock;
import java.net.URL;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.LookupTableJAI;
import javax.media.jai.RenderedOp;

import com.setvect.common.util.FileUtil;

/**
 * ������ �̹��� ����
 * 
 * @version $Id$
 */
public class ThumbnailImageConvert {

	/**
	 * ���� Ȯ���ڸ� ���� gif->jpg, jpg->jpg ����
	 * 
	 * @param orgFile
	 *            ���� ���� �̸�(��� ����)
	 * @param destFile
	 *            ������ ���� �̸�(��� ����)
	 * @param wishSizeW
	 *            �ִ� ����
	 * @param wishSizeH
	 *            �ִ� ����
	 */
	public static void toJPEGAny(String orgFile, String destFile,
			int wishSizeW, int wishSizeH) {

		String ext = FileUtil.getExt(orgFile);
		// gif
		if (ext.toLowerCase().endsWith(".gif")) {
			toJPEGfromGIF(orgFile, destFile, wishSizeW, wishSizeH);
		}
		// jpg
		else {
			toJPEG(orgFile, destFile, wishSizeW, wishSizeH);
		}

	}

	/**
	 * �ش� ����� �´� ������ JPEG �̹����� ����
	 * 
	 * @param orgFile
	 *            ���� ���� �̸�(��� ����)
	 * @param destFile
	 *            ������ ���� �̸�(��� ����)
	 * @param wishSizeW
	 *            �ִ� ����
	 * @param wishSizeH
	 *            �ִ� ����
	 */
	public static void toJPEG(String orgFile, String destFile, int wishSizeW,
			int wishSizeH) {
		RenderedOp src = JAI.create("fileload", orgFile);
		RenderedOp res_img = changeWidthScale(src, wishSizeW, wishSizeH);
		WritingImage.writeImageToFile(res_img, destFile, "JPEG");
	}

	/**
	 * * �ش� ����� �´� ������ JPEG �̹����� ����
	 * 
	 * @param orgURL
	 *            ���� �̹��� URL ���
	 * @param destFile
	 *            ������ ���� �̸�(��� ����)
	 * @param wishSizeW
	 *            �ִ� ����
	 * @param wishSizeH
	 *            �ִ� ����
	 */
	public static void toJPEG(URL orgURL, String destFile, int wishSizeW,
			int wishSizeH) {
		RenderedOp src = JAI.create("url", orgURL);
		RenderedOp res_img = changeWidthScale(src, wishSizeW, wishSizeH);
		WritingImage.writeImageToFile(res_img, destFile, "JPEG");
	}

	/**
	 * GIF �̹����� �ش� ����� �´� ������ JPEG �̹����� ����
	 * 
	 * @param orgFile
	 *            ���� ���� �̸�(��� ����)
	 * @param destFile
	 *            ������ ���� �̸�(��� ����)
	 * @param wishSizeW
	 *            �ִ� ����
	 * @param wishSizeH
	 *            �ִ� ����
	 */
	public static void toJPEGfromGIF(String orgFile, String destFile,
			int wishSizeW, int wishSizeH) {
		RenderedOp src = JAI.create("fileload", orgFile);
		RenderedOp res_img = bandSelectGIF(src);
		res_img = changeWidthScale(res_img, wishSizeW, wishSizeH);
		WritingImage.writeImageToFile(res_img, destFile, "JPEG");

	}

	/**
	 * GIF �̹����� �ش� ����� �´� ������ JPEG �̹����� ����
	 * 
	 * @param srcURL
	 *            ���� �̹��� URL ���
	 * @param destFile
	 *            ������ ���� �̸�(��� ����)
	 * @param wishSizeW
	 *            �ִ� ����
	 * @param wishSizeH
	 *            �ִ� ����
	 */
	public static void toJPEGfromGIF(URL srcURL, String destFile,
			int wishSizeW, int wishSizeH) {
		RenderedOp src = JAI.create("url", srcURL);
		RenderedOp res_img = bandSelectGIF(src);
		res_img = changeWidthScale(res_img, wishSizeW, wishSizeH);
		WritingImage.writeImageToFile(res_img, destFile, "JPEG");
	}

	/**
	 * �ش� ����� �´� ������ PNG �̹����� ����
	 * 
	 * @param orgFile
	 *            ���� ���� �̸�(��� ����)
	 * @param destFile
	 *            ������ ���� �̸�(��� ����)
	 * @param wishSizeW
	 *            �ִ� ����
	 * @param wishSizeH
	 *            �ִ� ����
	 */
	public static void toPNG(String orgFile, String destFile, int wishSizeW,
			int wishSizeH) {
		RenderedOp src = JAI.create("fileload", orgFile);
		RenderedOp res_img;
		res_img = changeWidthScale(src, wishSizeW, wishSizeH);
		WritingImage.writeImageToFile(res_img, destFile, "PNG");
	}

	/**
	 * �ش� ����� �´� ������ PNG �̹����� ����
	 * 
	 * @param srcURL
	 *            ���� �̹��� URL ���
	 * @param destFile
	 *            ������ ���� �̸�(��� ����)
	 * @param wishSizeW
	 *            �ִ� ����
	 * @param wishSizeH
	 *            �ִ� ����
	 */
	public static void toPNG(URL orgURL, String destFile, int wishSizeW,
			int wishSizeH) {
		RenderedOp src = JAI.create("url", orgURL);
		RenderedOp res_img = changeWidthScale(src, wishSizeW, wishSizeH);
		WritingImage.writeImageToFile(res_img, destFile, "PNG");
	}

	/**
	 * ���� �� �𸣰ڴ�. GIF ���ϸ��̼� �����ӿ� ���õ� �޼ҵ� �ΰ� ����.
	 * 
	 * @param src
	 *            �̹��� ����
	 * @return �̹��� ����
	 */
	private static RenderedOp bandSelectGIF(RenderedOp src) {
		ParameterBlock pb = null;
		RenderedOp res_img = null;
		if (src.getColorModel() instanceof IndexColorModel) {
			IndexColorModel icm = (IndexColorModel) src.getColorModel();
			int mapSize = icm.getMapSize();
			byte lutData[][] = new byte[3][mapSize];
			icm.getReds(lutData[0]);
			icm.getGreens(lutData[1]);
			icm.getBlues(lutData[2]);
			LookupTableJAI lut = new LookupTableJAI(lutData);
			res_img = JAI.create("lookup", src, lut);
		}
		else if (src.getColorModel().hasAlpha()) {
			pb = new ParameterBlock();
			pb.addSource(src);
			pb.add(new int[] { 0, 1, 2 });
			res_img = JAI.create("BandSelect", pb);
		}
		else {
			res_img = src;
		}
		return res_img;
	}

	/**
	 * ���� �̹����� ����� ����, ���� �ȼ��� �Է� �޾�, �ִ� ������ ��ҵǴ� ������ ���� ���� ���� ������ �����.
	 * 
	 * @param src
	 *            �̹��� �ҽ�
	 * @param wishSizeW
	 *            ���ϴ� ���� ũ��(�ȼ�)
	 * @param wishSizeH
	 *            ���ϴ� ���� ũ��(�ȼ�)
	 * @return ����� ������ �̹��� ����
	 */
	private static RenderedOp changeWidthScale(RenderedOp src, int wishSizeW,
			int wishSizeH) {
		int realW = src.getWidth();
		float ratioW = (float) wishSizeW / (float) realW;
		int realH = src.getHeight();
		float ratioH = (float) wishSizeH / (float) realH;

		// ����, ���� ��� ������ �ּ� ������ ��ü ���� ����
		float ratio = Math.min(ratioW, ratioH);

		// ���� �̹����� ����� ���� �̹��� ���� ���� ��� ���� �̹��� ������ ����� �ֱ�
		if (ratio > 1.0f) {
			ratio = 1.0f;
		}

		ParameterBlock scalePb = new ParameterBlock();
		scalePb.addSource(src);
		scalePb.add(ratio).add(ratio).add(0.0F).add(0.0F).add(
				new InterpolationNearest());
		RenderedOp res_img = CreateRenderedOp.getRenderOpFromParameterBlock(
				"scale", scalePb);
		return res_img;
	}
}
