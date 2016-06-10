
package com.cyd.lmplayer.helpers.lastfm;

import org.w3c.dom.Document;

import com.cyd.lmplayer.helpers.DomElement;


/**
 * The <code>Result</code> class contains the response sent by the server, i.e. the status (either ok or failed),
 * an error code and message if failed and the xml response sent by the server.
 *
 * @author ³ÂÑÅµÏ
 */
public class Result {

	public enum Status {
		OK,
		FAILED
	}

	protected Status status;
	protected String errorMessage = null;
	protected int errorCode = -1;
	protected int httpErrorCode = -1;

	protected Document resultDocument;

	protected Result(Document resultDocument) {
		this.status = Status.OK;
		this.resultDocument = resultDocument;
	}

	protected Result(String errorMessage) {
		this.status = Status.FAILED;
		this.errorMessage = errorMessage;
	}

	static Result createOkResult(Document resultDocument) {
		return new Result(resultDocument);
	}

	static Result createHttpErrorResult(int httpErrorCode, String errorMessage) {
		Result r = new Result(errorMessage);
		r.httpErrorCode = httpErrorCode;
		return r;
	}

	static Result createRestErrorResult(int errorCode, String errorMessage) {
		Result r = new Result(errorMessage);
		r.errorCode = errorCode;
		return r;
	}

	/**
	 * Returns if the operation was successful. Same as <code>getStatus() == Status.OK</code>.
	 *
	 * @return <code>true</code> if the operation was successful
	 */
	public boolean isSuccessful() {
		return status == Status.OK;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public int getHttpErrorCode() {
		return httpErrorCode;
	}

	public Status getStatus() {
		return status;
	}

	public Document getResultDocument() {
		return resultDocument;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public DomElement getContentElement() {
		if (!isSuccessful())
			return null;
		return new DomElement(resultDocument.getDocumentElement()).getChild("*");
	}

	@Override
	public String toString() {
		return "Result[isSuccessful=" + isSuccessful() + ", errorCode=" + errorCode + ", httpErrorCode=" + httpErrorCode + ", errorMessage="
				+ errorMessage + ", status=" + status+"]";
	}
}
