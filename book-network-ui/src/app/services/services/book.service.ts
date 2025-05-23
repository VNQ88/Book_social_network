/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { approveReturnedBook } from '../fn/book/approve-returned-book';
import { ApproveReturnedBook$Params } from '../fn/book/approve-returned-book';
import { BookResponse } from '../models/book-response';
import { borrowBook } from '../fn/book/borrow-book';
import { BorrowBook$Params } from '../fn/book/borrow-book';
import { getAllBooks } from '../fn/book/get-all-books';
import { GetAllBooks$Params } from '../fn/book/get-all-books';
import { getAllBooksBorrowed } from '../fn/book/get-all-books-borrowed';
import { GetAllBooksBorrowed$Params } from '../fn/book/get-all-books-borrowed';
import { getAllBooksByOwner } from '../fn/book/get-all-books-by-owner';
import { GetAllBooksByOwner$Params } from '../fn/book/get-all-books-by-owner';
import { getAllBooksReturned } from '../fn/book/get-all-books-returned';
import { GetAllBooksReturned$Params } from '../fn/book/get-all-books-returned';
import { getBook } from '../fn/book/get-book';
import { GetBook$Params } from '../fn/book/get-book';
import { PageResponeBookResponse } from '../models/page-respone-book-response';
import { PageResponeBorrowedBookResponse } from '../models/page-respone-borrowed-book-response';
import { returnBorrowedBook } from '../fn/book/return-borrowed-book';
import { ReturnBorrowedBook$Params } from '../fn/book/return-borrowed-book';
import { saveBook } from '../fn/book/save-book';
import { SaveBook$Params } from '../fn/book/save-book';
import { updateBookArchivedStatus } from '../fn/book/update-book-archived-status';
import { UpdateBookArchivedStatus$Params } from '../fn/book/update-book-archived-status';
import { updateBookShareableStatus } from '../fn/book/update-book-shareable-status';
import { UpdateBookShareableStatus$Params } from '../fn/book/update-book-shareable-status';
import { uploadBookCoverPicture } from '../fn/book/upload-book-cover-picture';
import { UploadBookCoverPicture$Params } from '../fn/book/upload-book-cover-picture';


/**
 * Book API
 */
@Injectable({ providedIn: 'root' })
export class BookService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getAllBooks()` */
  static readonly GetAllBooksPath = '/books';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooks$Response(params?: GetAllBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponeBookResponse>> {
    return getAllBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooks(params?: GetAllBooks$Params, context?: HttpContext): Observable<PageResponeBookResponse> {
    return this.getAllBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponeBookResponse>): PageResponeBookResponse => r.body)
    );
  }

  /** Path part for operation `saveBook()` */
  static readonly SaveBookPath = '/books';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveBook()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveBook$Response(params: SaveBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return saveBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveBook$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveBook(params: SaveBook$Params, context?: HttpContext): Observable<number> {
    return this.saveBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `uploadBookCoverPicture()` */
  static readonly UploadBookCoverPicturePath = '/books/cover/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadBookCoverPicture()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadBookCoverPicture$Response(params: UploadBookCoverPicture$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadBookCoverPicture(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadBookCoverPicture$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadBookCoverPicture(params: UploadBookCoverPicture$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadBookCoverPicture$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `borrowBook()` */
  static readonly BorrowBookPath = '/books/borrow/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `borrowBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook$Response(params: BorrowBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return borrowBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `borrowBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowBook(params: BorrowBook$Params, context?: HttpContext): Observable<number> {
    return this.borrowBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateBookShareableStatus()` */
  static readonly UpdateBookShareableStatusPath = '/books/shareable/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateBookShareableStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateBookShareableStatus$Response(params: UpdateBookShareableStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateBookShareableStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateBookShareableStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateBookShareableStatus(params: UpdateBookShareableStatus$Params, context?: HttpContext): Observable<number> {
    return this.updateBookShareableStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `returnBorrowedBook()` */
  static readonly ReturnBorrowedBookPath = '/books/borrow/return/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `returnBorrowedBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowedBook$Response(params: ReturnBorrowedBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return returnBorrowedBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `returnBorrowedBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowedBook(params: ReturnBorrowedBook$Params, context?: HttpContext): Observable<number> {
    return this.returnBorrowedBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `approveReturnedBook()` */
  static readonly ApproveReturnedBookPath = '/books/borrow/return/approve/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `approveReturnedBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturnedBook$Response(params: ApproveReturnedBook$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return approveReturnedBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `approveReturnedBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturnedBook(params: ApproveReturnedBook$Params, context?: HttpContext): Observable<number> {
    return this.approveReturnedBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateBookArchivedStatus()` */
  static readonly UpdateBookArchivedStatusPath = '/books/archived/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateBookArchivedStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateBookArchivedStatus$Response(params: UpdateBookArchivedStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateBookArchivedStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateBookArchivedStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateBookArchivedStatus(params: UpdateBookArchivedStatus$Params, context?: HttpContext): Observable<number> {
    return this.updateBookArchivedStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `getBook()` */
  static readonly GetBookPath = '/books/{book-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getBook()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBook$Response(params: GetBook$Params, context?: HttpContext): Observable<StrictHttpResponse<BookResponse>> {
    return getBook(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getBook$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getBook(params: GetBook$Params, context?: HttpContext): Observable<BookResponse> {
    return this.getBook$Response(params, context).pipe(
      map((r: StrictHttpResponse<BookResponse>): BookResponse => r.body)
    );
  }

  /** Path part for operation `getAllBooksReturned()` */
  static readonly GetAllBooksReturnedPath = '/books/returned';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllBooksReturned()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksReturned$Response(params?: GetAllBooksReturned$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponeBorrowedBookResponse>> {
    return getAllBooksReturned(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllBooksReturned$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksReturned(params?: GetAllBooksReturned$Params, context?: HttpContext): Observable<PageResponeBorrowedBookResponse> {
    return this.getAllBooksReturned$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponeBorrowedBookResponse>): PageResponeBorrowedBookResponse => r.body)
    );
  }

  /** Path part for operation `getAllBooksByOwner()` */
  static readonly GetAllBooksByOwnerPath = '/books/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllBooksByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksByOwner$Response(params?: GetAllBooksByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponeBookResponse>> {
    return getAllBooksByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllBooksByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksByOwner(params?: GetAllBooksByOwner$Params, context?: HttpContext): Observable<PageResponeBookResponse> {
    return this.getAllBooksByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponeBookResponse>): PageResponeBookResponse => r.body)
    );
  }

  /** Path part for operation `getAllBooksBorrowed()` */
  static readonly GetAllBooksBorrowedPath = '/books/borrowed';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllBooksBorrowed()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksBorrowed$Response(params?: GetAllBooksBorrowed$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponeBorrowedBookResponse>> {
    return getAllBooksBorrowed(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllBooksBorrowed$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllBooksBorrowed(params?: GetAllBooksBorrowed$Params, context?: HttpContext): Observable<PageResponeBorrowedBookResponse> {
    return this.getAllBooksBorrowed$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponeBorrowedBookResponse>): PageResponeBorrowedBookResponse => r.body)
    );
  }

}
