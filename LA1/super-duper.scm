
;; Scheme super-duper
;; Copies the the elements in 
;; 	source, count number of times
;; @author: Andre Maldonado
;; CS354 

(define (e-duper element cnt)
		(if (> cnt 1)
			(cons (car element)  (e-duper element ( - cnt 1)))  ;takes a single element from the "elements" usage and calls recursively
			element

		
		)
)

(define (super-duper source count)
		(if (list? source)  ;;checks if source is a list
			(if(pair? source)   ;;checks if source is a pair
				(e-duper (cons (super-duper (car source) count )  (super-duper (cdr source) count )) count)  ;;calls-one-duper
				source
			)
			source
		)
)
		
			

(display (super-duper 123 1))
(display "\n")
(display (super-duper 123 2))
(display "\n")
(display (super-duper '() 1))
(display "\n")
(display (super-duper '() 2))
(display "\n")
(display (super-duper '(x) 1))
(display "\n")
(display (super-duper '(x) 2))
(display "\n")
(display (super-duper '(x y) 1))
(display "\n")
(display (super-duper '(x y) 2))
(display "\n")
(display (super-duper '((a b) y) 3))
(display "\n")
(display (super-duper '. 2))
(display "\n")